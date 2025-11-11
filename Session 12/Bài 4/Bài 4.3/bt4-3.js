let a=0;
let b=1;
let c=a+b;
console.log(`the number is ${a}`);
console.log(`the number is ${b}`);


for(let i=1; i<=20; i=i+1){
  
    c=a+b ;
  a=b;
  b=c;
console.log(`the number is ${c}　${i}`);

    
}
