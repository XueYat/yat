package top.yat.designMode.abstracts;

public abstract class Game {

    /**
     * 启动游戏
     */
    protected abstract void runGame();

    /**
     * 选择人物
     */
    protected void choosePerson() {
    }

    /**
     * 开始游戏
     */
    protected abstract void startPlayGame();

    /**
     * 结束游戏
     */
    protected abstract void endPlayGame();

    /**
     * 模板方法
     */
    public final void play() {
        runGame();
        choosePerson();
        startPlayGame();
        endPlayGame();
    }

}
