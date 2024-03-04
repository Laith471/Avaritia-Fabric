package net.laith.avaritia.util.render;

public class Timer {
    private int update;
    private int max;
    private float[] frames;
    private float x;
    private int delay;
    private int timer;

    public Timer(float[] frames, int delay) {
        this.update = 0;
        this.frames = frames;
        this.timer = 0;
        this.delay = delay;
        this.max = frames.length - 1;
    }

    public void animationTimer() {
        if(timer < delay) {
            timer++;
        } else  {
            if (update < max) {
                x = frames[update];
                update++;
                timer = 0;
            } else {
                x = frames[max];
                update = 0;
            }
        }
    }

    public float getX() {
        return x;
    }
}

