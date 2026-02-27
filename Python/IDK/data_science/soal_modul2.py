def operation(n):
    code = "Output: "
    for value in n:
        a = 0
        b = 0
        a = value % 10
        b = value // 10
        hasil = a + b
        if hasil % 2 == 0:
            code += chr(value)
    return code


def soal1():
    array = []
    while True:
        n = int(input("Input two digits value: "))
        if n == 0:
            code = operation(array)
            break
        array.append(n)
    print(code)


def infinite_poweerr(physical, magic, defense):
    physical = int(physical)
    magic = int(magic)
    defense = float(defense)
    total_p = (2 * physical) + (3 * magic) - (0.5 * defense)
    return total_p


def soal2():
    total_p = []
    adv = int(input("total of adventurer: "))
    for _ in range(0, adv):
        py, ma, de = input("physical, magic, defense: ").split()
        total_p.append(infinite_poweerr(py, ma, de))
    for i in range(len(total_p)):
        print(f"Total power of adventurer {i + 1}: {total_p[i]}")


def output(n):
    i = 0
    for r in n:
        print(f"Total efficiency of robot{i + 1}: {n[i]}")
        i += 1


def gogreen(operational):
    a = []
    for speed, energy, precision in operational:
        total_efficiency = (3 * speed) + (4 * energy) - (2 * precision)
        a.append(total_efficiency)
    output(a)
    # speed, energy, precision = map(float, (speed,energy,precision))


def soal3():
    operational = []
    total_robot = int(input("total robot:"))
    for _ in range(total_robot):
        speed, energy, precision = input().split()
        speed = float(speed)
        energy = float(energy)
        precision = float(precision)
        operational.append((speed, energy, precision))
    gogreen(operational)


if __name__ == "__main__":
    # SOAL 1
    # soal1()

    # SOAL 2
    # soal2()

    # SOAL 3
    soal3()
    pass
