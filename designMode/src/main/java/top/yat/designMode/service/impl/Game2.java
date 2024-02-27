package top.yat.designMode.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yat.designMode.abstracts.Game;

@Slf4j
@Service("game2")
public class Game2 extends Game {
    @Override
    protected void runGame() {
        log.info("启动忍者神龟III...");
    }

    @Override
    protected void choosePerson() {
        log.info("1P选择了Raph ！");
    }

    @Override
    protected void startPlayGame() {
        log.info("Raph正在使用绝技 “火箭头槌” ");
    }

    @Override
    protected void endPlayGame() {
        log.info("Raph 掉进井盖里死了，游戏结束了！");
    }
}
