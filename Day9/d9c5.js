var demo1 = /** @class */ (function () {
    function demo1() {
        console.log("Hi Demo");
    }
    demo1.prototype.display = function () {
        console.log("Hi display");
    };
    return demo1;
}());
var d = new demo1();
d.display();
