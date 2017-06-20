   .data
Fac$$:
   .quad Fac$ComputeFac
   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $8, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq Fac$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   movq $10, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *0(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Fac$ComputeFac:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   movq -16(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L0
   movq $1, %rax
   movq %rax, -24(%rbp)
   jmp done$L1
else$L0:
   movq -16(%rbp), %rax
   pushq %rax
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
   pushq %rbx
   call *0(%rax)
   popq %rbx
   popq %rdx
   imulq %rdx, %rax
   movq %rax, -24(%rbp)
done$L1:
   movq -24(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
