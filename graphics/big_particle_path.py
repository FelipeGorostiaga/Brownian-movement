from matplotlib import pyplot as plt

with open('../output.txt', 'r') as f:
    lines = f.readlines()
    N = int(lines[0])
    X = [float(lines[x].split()[1]) for x in range(0, len(lines)) if x % (N + 2) == 2]
    Y = [float(lines[x].split()[2]) for x in range(0, len(lines)) if x % (N + 2) == 2]
    plt.style.use('ggplot')
    plt.plot(X, Y, color='red', marker='.')
    plt.axis([0,0.5,0,0.5])
    plt.xlabel('X')
    plt.ylabel('Y')
    plt.show()








