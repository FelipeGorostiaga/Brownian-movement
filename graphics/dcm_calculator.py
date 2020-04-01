import math

with open('../output.txt', 'r') as f:

    # t = [1,2,3.... n <= T]
    # todo: run with multiple simulations to calculate <DCM> for each value t

    # todo: read as arguments
    step = 1
    T = 100

    lines = f.readlines()
    N = int(lines[0])
    tc = float(lines[1])
    X_initial = float(lines[2].split()[1])
    Y_initial = float(lines[2].split()[2])
    X = [float(lines[x].split()[1]) for x in range(0, len(lines)) if x % (N + 2) == 2]
    Y = [float(lines[x].split()[2]) for x in range(0, len(lines)) if x % (N + 2) == 2]
    times = [float(lines[x].split()[0]) for x in range(0, len(lines)) if x % (N + 2) == 1]

    displacement = []
    for i in range(0, len(X)):
        displacement.append(math.sqrt( math.pow(X[i]-X_initial,2) + math.pow(Y[i]-Y_initial,2)))

    dcm = []
    for t in range(0, T, step):
        for i in range(0, len(times)):
            time = math.floor(times[i])
            if time >= t:
                dcm.append(math.pow(displacement[i],2))
                break

    print(dcm)
    print(len(dcm))

    # todo: graph results
