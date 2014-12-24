#!/usr/bin/env python
import time
import random
import math

if __name__ == '__main__':
    n = 0
    while(True):
        print math.exp(-n/100.) + 0.1 * random.random() + 0.1
        n += 1
        time.sleep(0.2 + random.random() * 0.5)
