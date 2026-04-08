#include <stdio.h>

// Check leap year
int isLeapYear(int year) {
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        return 1;
    return 0;
}

// Get number of days in a month
int getDays(int month, int year) {
    switch(month) {
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            return 31;
        case 4: case 6: case 9: case 11:
            return 30;
        case 2:
            return isLeapYear(year) ? 29 : 28;
        default:
            return 0;
    }
}

// Get day of week for 1 Jan of the year
// 0 = Sunday, 1 = Monday, ... 6 = Saturday
int getStartDay(int year) {
    int day = (year * 365 + (year / 4) - (year / 100) + (year / 400)) % 7;
    return day;
}

void printMonth(int month, int year, int *startDay) {
    char *months[] = {
        "January","February","March","April","May","June",
        "July","August","September","October","November","December"
    };

    int days = getDays(month, year);

    printf("\n\n  ----------- %s %d -----------\n", months[month-1], year);
    printf("  Sun  Mon  Tue  Wed  Thu  Fri  Sat\n");

    // Print spaces for first day
    for (int i = 0; i < *startDay; i++) {
        printf("     ");
    }

    for (int day = 1; day <= days; day++) {
        printf("%5d", day);

        if ((*startDay + day) % 7 == 0)
            printf("\n");
    }

    *startDay = (*startDay + days) % 7;
}

int main() {
    int year;

    printf("Enter year: ");
    scanf("%d", &year);

    int startDay = getStartDay(year);

    for (int month = 1; month <= 12; month++) {
        printMonth(month, year, &startDay);
    }

    printf("\n\n");
    return 0;
}