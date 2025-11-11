
for (let i=1; i<=100;i=i+1) {
    if (i % 3 === 0 && i % 5 === 0){
    console.log("FizzBuzz");
    continue;
    } else if(i % 3 === 0 ){
        console.log( "Fizz");
        continue;
    }else if( i % 5 === 0){
        console.log("Buzz");
        continue;
    }
    console.log(`The number is: ${i}`);
}