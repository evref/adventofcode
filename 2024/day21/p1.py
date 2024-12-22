class vec2:
    def __init__(self, *args):
        if len(args) == 1:
            self.x = args[0][0]
            self.y = args[0][1]
        elif len(args) == 2:
            self.x = args[0]
            self.y = args[1]

    def __add__(self, other):
        return vec2(self.x + other.x, self.y + other.y)
    
    def __sub__(self, other):
        return vec2(self.x - other.x, self.y - other.y)
    
    def __str__(self):
        return f"vec2({self.x}, {self.y})"
    
    def __eq__(self, other):
        return self.x == other.x and self.y == other.y
    
    def __ne__(self, other):
        return self.x != other.x or self.y != other.y

numpad = {'7': vec2(0,0), '8': vec2(1,0), '9': vec2(2,0), '4': vec2(0,1), '5': vec2(1,1), '6': vec2(2,1), '1': vec2(0,2), '2': vec2(1,2), '3': vec2(2,2), '0': vec2(1,3), 'A': vec2(2,3), 'illegal': vec2(0,3)}
movepad = {'^': vec2(1,0), 'A': vec2(2,0), '<': vec2(0,1), 'v': vec2(1,1), '>': vec2(2,1), 'illegal': vec2(0,0)}


def parse():
    pass

class keypad:
    def __init__(self, keypad_type: str):
        self.keys_pos = movepad
        if keypad_type == "numpad":
            self.keys_pos = numpad
        self.pos = self.keys_pos['A']

    def dist(self, c: chr) -> str:
        c_pos = self.keys_pos[c]
        dist = abs(c_pos.x-self.pos.x) + abs(c_pos.y-self.pos.y)

        # Move one tile at a time to avoid empty spaces (that is, the self.keys_pos['illegal'] position)
        path = ""
        diff = c_pos - self.pos
        while True:
            print(diff)
            if diff.x > 0 and diff == :
                path += '>'
                diff = diff + vec2(-1,0)
            elif diff.x < 0:
                path += '<'
                diff = diff + vec2(1,0)
            elif diff.y > 0:
                path += 'v'
                diff = diff + vec2(0,-1)
            elif diff.y < 0:
                path += '^'
                diff = diff + vec2(0,1)
            elif diff.y == 0 and diff.y == 0:
                path += 'A'
                break
            # Make sure to calculate dist and return as before for testing, before deployment
        
        return path
    
    def type_code(self, code: str):
        sum = 0
        for c in code:
            # Add the distance value plus one for pressing it
            sum += self.dist(c)+1

        return sum



data = parse()
kp = keypad("numpad")
print(kp.dist("7"))

v1 = vec2((1,2))
v2 = vec2(10,10)
v3 = v1-v2
print(v3)