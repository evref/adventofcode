import numpy as np

with open('input', 'r') as file:
    data = [list(map(int, line.split())) for line in file]

num_safe_reports = 0
for row in data:
    row = np.array(row)
    res = row[1:] - row[:-1]
    safe_mask = np.abs(res) <= 3

    safe_diff = np.abs(np.sum(safe_mask)) == len(safe_mask)
    safe_signs = (np.all(res > 0) or np.all(res < 0))
    num_safe_reports += safe_diff and safe_signs

print(num_safe_reports)