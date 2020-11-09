package ru.armagidon.snake.utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameTimer
{
    private final Timer timer = new Timer();
    private final Map<Integer, Runnable> tasks = new ConcurrentHashMap<>();
    private final Map<Integer, Runnable> stoppedTasks = new ConcurrentHashMap<>();
    private final Random random = new Random(123456789);

    private final int SPEED;

    public GameTimer(int speed) {
        this.SPEED = speed;
    }

    public void start(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tasks.values().forEach(Runnable::run);
            }
        },  SPEED, SPEED);
    }

    public int addTask(Runnable runnable){
        int id = random.nextInt();
        tasks.put(id, runnable);
        return id;
    }

    public void pauseTask(int id){
        if (!tasks.containsKey(id)) return;

        stoppedTasks.put(id, tasks.remove(id));
    }

    public void resumeTask(int id){
        if (!stoppedTasks.containsKey(id)) return;
        Runnable runnable = stoppedTasks.get(id);
        tasks.put(id, runnable);
        stoppedTasks.remove(id);
    }

    public void cancel(){
        tasks.clear();
        stoppedTasks.clear();
        timer.cancel();
    }

}
