package com.javarush.test.level28.lesson06.home01;

/**
 * Created by Andriana on 05.11.2016.
 */
public class MyThread extends Thread
{
    private static int prior = 0;
    public MyThread()
    {
        super();
        if (++prior > 10)
            prior=1;
        setPriority(prior);
    }

    public MyThread(Runnable target)
    {
        super(target);
        if (++prior > 10)
            prior=1;
        setPriority(prior);
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        if (++prior > 10)
            prior=1;
        setPriority(prior);
    }

    public MyThread(String name)
    {
        super(name);
        if (++prior > 10)
            prior=1;
        setPriority(prior);
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        if (++prior > 10)
            prior=1;
        setPriority(prior);
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        if (++prior > 10)
            prior=1;
        setPriority(prior);
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        if (++prior > 10)
            prior=1;
        setPriority(prior);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        if (++prior > 10)
            prior=1;
        setPriority(prior);
    }
}
