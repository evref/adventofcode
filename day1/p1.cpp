#include <iostream>
#include <list>
using namespace std;


void p1_extract_value(int* value, string line) {
    list<int> digit_list;

    for (int i = 0; i < line.length(); i++) {
        if (isdigit(line[i])) {
            digit_list.push_back(line[i]-'0');
        }
    }

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
                *value = first_num * 10 + first_num;
                break;
            }
        default:
            *value = digit_list.front() * 10 + digit_list.back();
    }
}