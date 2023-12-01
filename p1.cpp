#include <iostream>
#include <fstream>
#include <list>
using namespace std;


int char_to_int(char c) {
    return c - '0';
}

int extract_value(string line) {
    list<int> digit_list;

    for (int i = 0; i < line.length(); i++) {
        if (isdigit(line[i])) {
            digit_list.push_back(char_to_int(line[i]));
        }
    }

    int value;
    switch (digit_list.size()) {
        case 0: 
            {
                string error_msg = "ERROR: Line contains no digits.";
                throw invalid_argument(error_msg);
                break;
            } 
        case 1: 
            {
                int first_num = digit_list.front();
                value = first_num * 10 + first_num;
                break;
            }
        default:
            value = digit_list.front() * 10 + digit_list.back();
    }

    return value;
}

int p1(string input_data_path) {
    int total = 0;
    ifstream file;
    string line;
    file.open(input_data_path);

    if (file.is_open()) {
        while (getline(file, line)) {
            total += extract_value(line);
        }
    } else {
        throw invalid_argument("ERROR: The file could not be opened");
    }
    
    file.close();

    return total;
}

int main() {
    string input_data_path = "input";

    cout << p1(input_data_path) << endl;
    return 0;
}
