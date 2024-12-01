import numpy as np

data = np.genfromtxt("input", autostrip=True, dtype=int)
transposed = data.T
similarity_scores = [val * np.sum(transposed[1] == val) for val in transposed[0]]
similarity_score = np.sum(similarity_scores)

print(similarity_score)