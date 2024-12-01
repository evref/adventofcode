import numpy as np

data = np.genfromtxt("input", autostrip=True, dtype=int)

transposed = data.T
expanded = np.expand_dims(transposed[1], axis=1)
similarity_scores = transposed[0] * np.sum(expanded == transposed[0], axis=0)
similarity_score = np.sum(similarity_scores)

print(similarity_score)