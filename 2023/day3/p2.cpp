#include <iostream>
#include <list>
using namespace std;


void build_num(int* num, int i, string line) {
    string buffer(16, ' ');
    buffer[8] = line[i];
    int dir = 1;
    for (int j = 0; j < 2; j++) {
        int buffer_idx = 8 + dir;
        int h = i + dir;
        while (true) {
            if (h < 0 || h > line.size()-1) break;
            else if (isdigit(line[h])) {
                buffer[buffer_idx] = line[h];
                buffer_idx += dir;
                h += dir;
            } else break;
        }
        dir = -1;
    }

    *num = stoi(buffer);
}

void numbers_line(list<int>* nums_adjacent, int i, string line, bool is_cur_line = false) {
    if (isdigit(line[i])) {
        int num;
        build_num(&num, i, line);
        nums_adjacent->push_back(num);
    } else {
        int num;
        if (i > 0) {
            if (isdigit(line[i-1])) {
                build_num(&num, i-1, line);
                nums_adjacent->push_back(num);
            }
        } 
        if (i < line.size()-1) {
            if (isdigit(line[i+1])) {
                build_num(&num, i+1, line);
                nums_adjacent->push_back(num);
            }
        }
    }
}

void compute_line_p2(int* total, string line, string last_line, string next_line) {
    list<int> nums_adjacent;
    for (int i = 0; i < line.length(); i++) {
        if (line[i] == '*') {
            if (last_line != "") numbers_line(&nums_adjacent, i, last_line);
            if (next_line != "") numbers_line(&nums_adjacent, i, next_line);
            numbers_line(&nums_adjacent, i, line, true);

            if (nums_adjacent.size() == 2) *total += nums_adjacent.front() * nums_adjacent.back();
            nums_adjacent.clear();
        }
    }
}