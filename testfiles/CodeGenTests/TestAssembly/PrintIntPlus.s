   .data


   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $100, %rax
   pushq %rax
   movq $10, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

