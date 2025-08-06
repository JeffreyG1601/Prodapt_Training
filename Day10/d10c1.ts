// Rest operator in function parameters
function sum(...numbers: number[]): number {
  return numbers.reduce((acc, curr) => acc + curr, 0);
}

const result = sum(1, 2, 3, 4); // 10

// Spread operator to expand arrays
const arr1 = [1, 2, 3];
const arr2 = [4, 5, ...arr1]; // [4, 5, 1, 2, 3]

// Spread operator to copy objects
const obj1 = { a: 1, b: 2 };
const obj2 = { ...obj1, c: 3 }; // { a: 1, b: 2, c: 3 }

console.log(result);
console.log(arr2);
console.log(obj2);