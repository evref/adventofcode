#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>
using namespace std;


void compute_wins_line(int* num_of_wins, vector<string>* winning_nums, vector<string>* my_nums) {
    while (my_nums->size() > 0) {
        string my_cur_num = my_nums->back();
        my_nums->pop_back();
        for (auto i = winning_nums->begin(); i != winning_nums->end(); i++) {
            if (my_cur_num == *i) {
                *num_of_wins += 1;
                break;
            }
        }
    }
}

void interpret_line(vector<string>* winning_nums, vector<string>* my_nums, string line) {
    string buffer;
    bool passed_bar = false;
    for (int i = 10; i < line.size(); i++) {
        if (isdigit(line[i])) {
            buffer += line[i];
        } else if (line[i] == '|') passed_bar = true;
        
        if ((!isdigit(line[i]) && buffer != "") || i == line.size()-1) {
            if (passed_bar) my_nums->push_back(buffer);
            else winning_nums->push_back(buffer);
            buffer = "";
        }
    }
}

void solve_line(int* total, string line) {
    vector<string> winning_nums, my_nums;
    interpret_line(&winning_nums, &my_nums, line);
    
    int num_of_wins = 0;
    compute_wins_line(&num_of_wins, &winning_nums, &my_nums);
    cout << num_of_wins << endl;
    cout << endl;

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