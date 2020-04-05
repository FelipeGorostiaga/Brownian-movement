from matplotlib import pyplot as plt
import math
import sys
import subprocess
import numpy as np
from least_squares import *

name, n, time, runs = sys.argv
T = int(time)
T2 = int(T/2)

runs = int(runs)
dcm_runs = []
for r in range(0, runs):
    command = 'java -jar ../target/tp3-1.0-SNAPSHOT-jar-with-dependencies.jar -n {n} -t {time}'.format(n=n,time=time)
    p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    lines =  p.stdout.readlines()
    N = int(lines[0])
    tc = float(lines[1])

    X = [float(lines[x].split()[1]) for x in range(0, len(lines)) if x % (N + 2) == 2]
    Y = [float(lines[x].split()[2]) for x in range(0, len(lines)) if x % (N + 2) == 2]
    times = [float(lines[x].split()[0]) for x in range(0, len(lines)) if x % (N + 2) == 1]

    X = X[len(X)//2:]
    Y = Y[len(Y)//2:]
    times = times[len(times)//2:]
    X_initial = X[0]
    Y_initial = Y[0]

    displacement = []
    for i in range(0, len(X)):
        displacement.append(math.pow(X[i]-X_initial,2) + math.pow(Y[i]-Y_initial,2))

    # T = 100
    t0 = int(T/2)

    dcm = []
    for t in range(t0, T):
        for i in range(0, len(times)):
            time = math.floor(times[i])
            if time >= t:
                dcm.append(displacement[i])
                break
    dcm_runs.append(dcm)

avg_dcm = []
errors = []
for i in range(0, T2):
    error = []
    sum = 0
    for j in range(0, len(dcm_runs)):
        error.append(dcm_runs[j][i])
        sum += dcm_runs[j][i]
    avg_dcm.append(sum / runs)
    errors.append(np.std(error))

for i in range(0,len(errors)):
    errors[i] = errors[i] / math.sqrt(int(n))

x =  range(0,T2)

points = [[0 for x in range(2)] for y in range(len(avg_dcm))]
for i in range(0,len(avg_dcm)):
    points[i][0] = x[i]
    points[i][1] = avg_dcm[i]

line = calc_line(points)
print_line(line)
yl = [line[0]*xx + line[1]  for xx in x]
plt.style.use('seaborn')
plt.plot(x,yl)
plt.errorbar(x, avg_dcm, yerr=line[0])
plt.xlabel('Time (s)')
plt.ylabel('<Desplazamiento Cuadratico Medio> (m^2/s)')
plt.show()






















