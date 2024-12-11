

def parse():
    with open("input", 'r') as file:
        data = list(map(int, file.readline().split()))

    return data

def blink(num):
    if num == 0:
        return [1]
    str_num = str(num)
    if len(str_num) % 2 == 0:
        half_idx = int(len(str_num)/2)
        num1 = int(str_num[:half_idx])
        num2 = int(str_num[half_idx:])
        return [num1, num2]
    return [num*2024]

def solve1(data):
    for i in range(25):
        print(i)
        new_data = []
        for num in data:
            new_data.extend(blink(num))
        data = new_data

    return len(data)
            

data = parse()
solution = solve1(data)
print(solution)