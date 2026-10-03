package jp.co.evo.phase;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import ga.framework.exception.GaBuisinessException;
import ga.framework.exception.GaSystemException;
import ga.framework.logic.common.GaContext;
import ga.framework.logic.core.phase.GaPhase;
import ga.service.log.LogLevel;
import ga.service.log.LogService;
import jp.co.evo.common.CommonStrings;
import jp.co.evo.common.HttpService;

/**
 * 初期化フェーズ
 * <p>
 * メインフェーズ実行前の初期化フェーズ処理
 * </p>
 */
public class InitPhase implements GaPhase {

	/**
	 * 初期化処理実行
	 * <p>
	 * 0ADを起動する。
	 * </p>
	 */

	LogService log = new LogService(InitPhase.class);

	@Override
	public void execute(GaContext context) {
		String playerId = "1";
		String url = CommonStrings.IP + ":" + CommonStrings.PORT;
		try {
			// 起動
			log.print(LogLevel.DEBUG, "起動確認");
			boolean running = ProcessHandle.allProcesses()
				    .anyMatch(process -> {
				        ProcessHandle.Info info = process.info();

				        String command = info.command().orElse("");
				        String[] arguments = info.arguments().orElse(new String[0]);

				        if (command.equals("/usr/games/0ad"))
				            return true;

				        return Arrays.stream(arguments)
				            .anyMatch(arg -> arg.equals("/usr/games/0ad"));
				    });
			if (running)
				throw new GaBuisinessException("プロセスはすでに起動済みです。");

			log.print(LogLevel.DEBUG, "0ADを起動");
			log.print(LogLevel.DEBUG, url);
			new ProcessBuilder("0ad", "--rl-interface=" + url).start();

			// Httpサービスセットアップ
			log.print(LogLevel.DEBUG, "Httpサービスセットアップ");
			HttpService http = HttpService.factory();
			http.initialize(url);
			Thread.sleep(5000);

			// マップ読み込み
			log.print(LogLevel.DEBUG, "マップ読み込み");
			String json = Files.readString(Path.of("opt/arcadia.json"), StandardCharsets.UTF_8);
			http.sendPost("reset?playerID=" + playerId, json);

			// step実行
			log.print(LogLevel.DEBUG, "step実行");
			HttpResponse<String> response = http.sendPost("step", "{" + playerId + ":{}}");
			log.print(LogLevel.DEBUG, "Response:\n" + response.body());

		} catch (IOException e) {
			throw new GaSystemException("エラーが発生", e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
