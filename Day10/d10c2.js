// Example: filter method and lambda (arrow) expression in TypeScript
var numbers = [1, 2, 3, 4, 5, 6];
// Use filter with a lambda (arrow) function to get even numbers
var evenNumbers = numbers.filter(function (num) { return num % 2 === 0; });
console.log('Even numbers:', evenNumbers); // Output: [2, 4, 6]
