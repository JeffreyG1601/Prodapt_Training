var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
// Rest operator in function parameters
function sum() {
    var numbers = [];
    for (var _i = 0; _i < arguments.length; _i++) {
        numbers[_i] = arguments[_i];
    }
    return numbers.reduce(function (acc, curr) { return acc + curr; }, 0);
}
var result = sum(1, 2, 3, 4); // 10
// Spread operator to expand arrays
var arr1 = [1, 2, 3];
var arr2 = __spreadArray([4, 5], arr1, true); // [4, 5, 1, 2, 3]
// Spread operator to copy objects
var obj1 = { a: 1, b: 2 };
var obj2 = __assign(__assign({}, obj1), { c: 3 }); // { a: 1, b: 2, c: 3 }
console.log(result);
console.log(arr2);
console.log(obj2);
