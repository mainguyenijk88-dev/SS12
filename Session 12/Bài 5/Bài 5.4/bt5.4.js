// let numberrow=5;
// let numbercol=5;

// for( let i=1; i<=numberrow;i++  ){
//     let result="";
//       for(let j =1; j<=i; j++ ){
//       result += "*"  
//       }
      
// console.log(result);

// }

// let numberrow=5;
// let numbercol=5;
// for( let i =0; i<=numberrow; i++){
//     let result="";
//     for( let j =1; j<=numbercol-i;j++ ){
//         result +="*"
//     }
//     console.log(result);
// }
let numberrow =5;
let numbercol=5;
for( let i=1;i<= numberrow; i++){
    let result="";
    
   for( let s=1; s<=numbercol-i;s++){
    result += " ";
   }
    for( let k= 1; k<=i; k++){
        result +="*";
    }
    console.log(result);
    
}
