   .data
Breaker$$:
   .quad Breaker$recurse


   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $8, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq Breaker$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   movq $20, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

Breaker$recurse:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

