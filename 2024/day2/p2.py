import numpy as np

with open('input', 'r') as file:
    data = [list(map(int, line.split())) for line in file]

def is_safe(row: str):
    res = row[1:] - row[:-1]
    safe_mask = np.abs(res) <= 3

    safe_diff = np.abs(np.sum(safe_mask)) == len(safe_mask)
    safe_signs = (np.all(res > 0) or np.all(res < 0))

    return safe_diff and safe_signs

num_safe_reports = 0
for row in data:
    row = np.array(row)
    if not is_safe(row):
        for i in range(len(row)):
            row_slice = np.append(row[:i], row[i+1:])
            if is_safe(row_slice):
                num_safe_reports += 1
                break
    else:
        num_safe_reports += 1

print(num_safe_reports)