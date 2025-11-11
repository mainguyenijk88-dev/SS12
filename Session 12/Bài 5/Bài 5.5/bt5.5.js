
let amount = 10000000;
let month = 12;
let profit = 0.05;
let totalamount = amount;

 for( i=1; i<=12; i++){
    let interest = totalamount*profit;
    totalamount += interest;
    
    
 }
console.log(totalamount);

