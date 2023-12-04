#include <iostream>
#include <fstream>
#include <list>
#include <cmath>
using namespace std;


void comput_wins_line(int* num_of_wins, list<int>* winning_nums, list<int>* my_nums) {
    int winning_nums_array[winning_nums->size()];
    for (int i = 0; i < winning_nums->size(); i++) {
        winning_nums_array[i] = winning_nums->front();
        winning_nums->pop_front();
    }

    while (my_nums->size() > 0) {
        int my_cur_num = my_nums->back();
        my_nums->pop_back();
        for (int i = 0; i < winning_nums->size(); i++) {
            if (my_cur_num == winning_nums_array[i]) {
                *num_of_wins++;
                break;
            }
        }
    }
}

void interpret_line(list<int>* winning_nums, list<int>* my_nums, string line) {
    string buffer;
    bool passed_bar = false;
    for (int i = 10; i < line.size(); i++) {
        if (isdigit(line[i])) {
            buffer += line[i];
        } else if (line[i] == '|') passed_bar = true;
        else if (buffer != "") {
            if (passed_bar) my_nums->push_back(stoi(buffer));
            else winning_nums->push_back(stoi(buffer));
            buffer = "";
        }
    }
}

void solve_line(int* total, string line) {
    list<int> winning_nums, my_nums;
    interpret_line(&winning_nums, &my_nums, line);
    
    int num_of_wins = 0;
    comput_wins_line(&num_of_wins, &winning_nums, &my_nums);
    cout << num_of_wins << endl;

    *total += pow(2, num_of_wins-1);
}

int main() {
    string input_data_path = "input";

    fstream file;
    string line;
    file.open(input_data_path);

    int total = 0;
    if (file.is_open()) {
        while (getline(file, line)) {
            solve_line(&total, line);
        }
    }

    cout << total << endl;

    return 0;
}