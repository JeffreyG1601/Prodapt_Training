// Example: filter method and lambda (arrow) expression in TypeScript

const numbers = [1, 2, 3, 4, 5, 6];

// Use filter with a lambda (arrow) function to get even numbers
const evenNumbers = numbers.filter(num => num % 2 === 0);

console.log('Even numbers:', evenNumbers); // Output: [2, 4, 6]
