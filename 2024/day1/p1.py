import numpy as np
import re

data = np.genfromtxt("input", autostrip=True, dtype=int)
transposed = data.T
sorted = np.sort(transposed)
diffs = np.abs(np.diff(sorted, axis=0))
diff = np.sum(diffs)

print(diff)