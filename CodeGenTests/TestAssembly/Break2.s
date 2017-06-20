   .data
Breaker$$:
   .quad Breaker$call
   .quad Breaker$call1
   .quad Breaker$call2


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

Breaker$call:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   pushq $0
   pushq $0
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -16(%rbp)
   movq $420, %rax
   movq %rax, -24(%rbp)
   movq -24(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

Breaker$call1:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movq $69, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *16(%rax)
   movq %rax, -16(%rbp)
   movq $9, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

Breaker$call2:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq $0, %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

