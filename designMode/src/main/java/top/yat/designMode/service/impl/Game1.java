package top.yat.designMode.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yat.designMode.abstracts.Game;

@Slf4j
@Service("game1")
public class Game1 extends Game {
    @Override
    protected void runGame() {
        log.info("启动魂斗罗II...");
    }

    @Override
    protected void startPlayGame() {
        log.info("1P正在使用S弹打aircraft...");
    }

    @Override
    protected void endPlayGame() {
        log.info("1P被流弹打死了，游戏结束！");
    }
}
