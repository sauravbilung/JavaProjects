# Java Concurrency: Similar Terms Cheat Sheet

## wait() vs sleep()
wait() releases the lock and needs notify(); sleep() just pauses the thread without releasing the lock.

## wait() vs join()
wait() waits for a condition (with notify()); join() waits for a thread to die.

## sleep() vs join()
sleep() delays for a time; join() waits for another thread to finish.

## Blocking vs Waiting
Blocking = stuck on a resource; Waiting = voluntarily paused until notified.

## notify() vs notifyAll()
notify() wakes one waiting thread; notifyAll() wakes all waiting threads.

## Thread vs Runnable vs Callable
Thread is a class; Runnable has no return; Callable returns a value and can throw exceptions.

## Synchronized block vs method
Block synchronizes only part of the code; method syncs the entire method.

## Lock vs synchronized
Lock (e.g. ReentrantLock) is more flexible and powerful than synchronized.

## Volatile vs synchronized
volatile ensures visibility only; synchronized ensures visibility and atomicity.

## Atomic vs synchronized
Atomic classes give lock-free thread-safe operations.

## Thread-safe vs Concurrent
Thread-safe = safe in multithreaded env; Concurrent = designed for high concurrency, better performance.

