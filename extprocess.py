#!/usr/bin/env python
import time
import random
import math

if __name__ == '__main__':
    n = 0
    for _ in range(50):
        print math.exp(-n/10.) + 0.1 * random.random() + 0.1
        n += 1
        time.sleep(0.05 + random.random() * 0.05)
