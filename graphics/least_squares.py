
# receives matrix with points. m = [[x1,y1],[x2,y2],[x3,y3]]
def calc_line(points):
    line = []
    n = len(points)
    sum_xy = sum_x2 = sum_x = sum_y = 0
    for i in range(n):
        sum_x += points[i][0]
        sum_y += points[i][1]
        sum_x2 += points[i][0] ** 2
        sum_xy += points[i][0] * points[i][1]

    m = (n * sum_xy - sum_x * sum_y) / (n * sum_x2 - (abs(sum_x)**2))
    c = (sum_y * sum_x2 - sum_x * sum_xy) / (n * sum_x2 - (abs(sum_x)**2))
    line.append(m)
    line.append(c)
    return line


def get_y(matrix):
    y = []
    for i in range(len(matrix)):
        y.append(matrix[i][1])
    return y


def get_A(matrix):
    rows = len(matrix)
    columns = 3
    A = np.array([[0 for x in range(columns)] for y in range(rows)])

    for i in range(rows):
        A[i][0] = matrix[i][0] ** 2
        A[i][1] = matrix[i][0]
        A[i][2] = 1
    return A


def print_line(line):
    print("Solución lineal:\ny = %fx%s%f" % (line[0], "+" if line[1] >= 0 else "", line[1]))