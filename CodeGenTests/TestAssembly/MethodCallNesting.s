   .data
Add$$:
   .quad Add$add5

   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $8, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq Add$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   movq $10, %rax
   pushq %rax
   movq $20, %rax
   pushq %rax
   movq $30, %rax
   pushq %rax
   movq $40, %rax
   pushq %rax
   movq $8, %rdi
   call mjcalloc
   leaq Add$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   movq $8, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq Add$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   movq $109, %rax
   pushq %rax
   movq $458, %rax
   pushq %rax
   movq $434, %rax
   pushq %rax
   movq $2319, %rax
   pushq %rax
   movq $43211, %rax
   pushq %rax
   popq %r9
   popq %r8
   popq %rcx
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *0(%rax)
   popq %rbx
   pushq %rax
   movq $7, %rax
   pushq %rax
   movq $8, %rax
   pushq %rax
   movq $9, %rax
   pushq %rax
   movq $10, %rax
   pushq %rax
   popq %r9
   popq %r8
   popq %rcx
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   pushq %rax
   popq %r9
   popq %r8
   popq %rcx
   popq %rdx
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

Add$add5:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq %rcx
   pushq %r8
   pushq %r9
   movq -16(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   popq %rdx
   addq %rdx, %rax
   pushq %rax
   movq -32(%rbp), %rax
   popq %rdx
   addq %rdx, %rax
   pushq %rax
   movq -40(%rbp), %rax
   popq %rdx
   addq %rdx, %rax
   pushq %rax
   movq -48(%rbp), %rax
   popq %rdx
   addq %rdx, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

