### 如何学习并发编码

- 建立知识网络，建立一张全景图。并发领域可以抽象为三个问题：分工，同步，互斥

    1. 分工
        - 分工就是指如何将任务分到多个线程上，类似平时做项目，项目经理将任务拆分，分配给不同的人一样。分工的好坏决定了并发程序的性能。
        并发领域内分工是一件重要而且复杂的事情，不过已经有很多成果，帮助我们做这些事情，比如 Java SDK中提供的Executor, Fork/Join,
        Future 本质上都是一种分工方法，还有一些设计模式，比如生产者-消费者，Thread-Per-Message, Worker Thread 模式都是用来帮助
        我们可以进行更好的分工
    
    2. 同步
        - 指一个线程执行完了一个任务，如何通知执行后续任务的线程开工，也就是线程之间的协作，协作一般是和分工相关的，Java SDK 并发包里的
        Executor, Fork/Join, Future 本质上都是分工方法，但同时也能解决协作问题。线程协作问题，基本上都可以描述为这样的一个问题：当某个
        条件不满足时，线程需要等待，当某个条件满足时，线程需要被唤醒执行。在Java并发领域，解决协作的问题的核心技术是管程，上面提到的所有线程协作
        技术底层都是利用管程解决的，管程是一种解决并发问题的通用模型，可以说管程是解决并发问题的万能钥匙。
    
    3. 互斥
        - 分工和同步强调的都是性能，互斥强调的是正确性，并发环境下多个线程对同一个共享变量进行操作，如果不做任何控制，那么结果往往是未知的，
        未知的源头，是因为CPU缓存，线程切换，编译器优化，导致的 可见性，原子性，顺序性 三个问题，Java内存模型可以解决可见性，与顺序性，
        解决线程安全问题的核心还是互斥。互斥就是指同一时刻，只允许一个线程访问共享变量。实现互斥的核心技术就是锁，Java 提供的synchronized,
        SDK里的各种Lock都可以解决互斥问题。现实往往是解决了一个问题又会带来另一个问题，使用锁的同时往往会带来性能问题，根据不同的场景我们可以进行
        不同的优化，比如Java SDK中提供的 ReadWriteLock, StampedLock 就可以优化读多写少场景下锁的性能。还可以使用无锁的数据结构，比如Java SDK里
        提供的原子类都是基于无锁技术(CAS)实现的,还有一些其他的方案，原理是不共享变量，或变量值允许读，这方面Java 提供了 Thread Local,final，还有
        一种copy-on-write的模式。使用锁除了注意性能问题外，还需要注意死锁问题。
        
- 看本质
    1. 学习的时候不光要分析概念和理论，更要分析这些东西是怎么来，背后的理论基础是什么，以及用来解决什么问题