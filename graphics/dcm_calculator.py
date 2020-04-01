from matplotlib import pyplot as plt
import math
import sys
import subprocess

name, n, time, runs = sys.argv
step = 1
T = int(time)
runs = int(runs)
dcm_runs = []
for r in range(0, runs):
    command = 'java -jar ../target/tp3-1.0-SNAPSHOT-jar-with-dependencies.jar -n {n} -t {time}'.format(n=n,time=time)
    p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    lines =  p.stdout.readlines()
    # lines = f.readlines()
    N = int(lines[0])
    tc = float(lines[1])
    X_initial = float(lines[2].split()[1])
    Y_initial = float(lines[2].split()[2])
    X = [float(lines[x].split()[1]) for x in range(0, len(lines)) if x % (N + 2) == 2]
    Y = [float(lines[x].split()[2]) for x in range(0, len(lines)) if x % (N + 2) == 2]
    times = [float(lines[x].split()[0]) for x in range(0, len(lines)) if x % (N + 2) == 1]

    displacement = []
    for i in range(0, len(X)):
        displacement.append(math.pow(X[i]-X_initial,2) + math.pow(Y[i]-Y_initial,2))

    dcm = []
    for t in range(0, T, step):
        for i in range(0, len(times)):
            time = math.floor(times[i])
            if time >= t:
                str = 'adding {} for time {}'.format(times[i],t)
                print(str)
                dcm.append(displacement[i])
                break
    dcm_runs.append(dcm)

avg_dcm = []
for i in range(0, T):
    sum = 0
    for j in range(0, len(dcm_runs)):
        sum += dcm_runs[j][i]
    avg_dcm.append(sum / runs)

x =  range(0,T)
plt.style.use('ggplot')
plt.plot(x, avg_dcm)
plt.xlabel('Time')
plt.ylabel('DCM')
title = 'Average DCM over {r} runs'.format(r=runs)
plt.title(title)
plt.show()



