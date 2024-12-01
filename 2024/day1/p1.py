import numpy as np
import re

data = np.genfromtxt("input", autostrip=True, dtype=int)
transposed = data.T
sorted = np.sort(transposed)
diffs = np.abs(sorted[0]-sorted[1])
diff = np.sum(diffs)

print(diff)