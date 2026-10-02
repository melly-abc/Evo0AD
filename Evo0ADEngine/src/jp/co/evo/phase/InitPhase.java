package jp.co.evo.phase;

import java.io.IOException;

import ga.framework.exception.GaSystemException;
import ga.framework.logic.common.GaContext;
import ga.framework.logic.core.phase.GaPhase;
import jp.co.evo.common.CommonStrings;
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
	@Override
	public void execute(GaContext context) {
		try {
			new ProcessBuilder(
				    "0ad",
				    "--rl-interface="+CommonStrings.IP+":"+CommonStrings.PORT
				).start();
		} catch (IOException e) {
			throw new GaSystemException("エラーが発生");
		}
	}

}
