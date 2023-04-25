# 工程简介

### CPU飙升Demo

1.  使用`top`命令查看cpu占比高的java进程

2.  `ps -mp PID -o THREAD,tid,time` 找到导致cpu飙升的线程对应的TID

3.  TID对应的是十进制的，使用`printf "%x\n" TID`转换成十六进制的

4.  `jstack PID |grep TID -A 20` 查看详细堆栈信息，输出下面20行堆栈信息，可以看到具体造成cpu飙升的类和行数

