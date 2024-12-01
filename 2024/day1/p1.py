import numpy as np
import re

data = np.genfromtxt("input", autostrip=True, dtype=int)
transposed = data.T
sorted = np.sort(transposed)
diffs = [abs(sorted[0][i]-sorted[1][i]) for i in range(len(sorted[0]))]
diff = np.sum(diffs)

print(diff)