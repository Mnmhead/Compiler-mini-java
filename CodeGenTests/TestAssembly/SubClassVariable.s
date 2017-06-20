   .data
A$$:
   .quad A$setP
B$$:
   .quad A$setP
   .quad B$mainB
   .quad B$getP

   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $24, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq B$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *8(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

A$setP:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq $900, %rax
   movq -8(%rbp), %rbx
   movq %rax, 8(%rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   movq %rax, %rdi
   call put
   movq $1, %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

B$mainB:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *0(%rax)
   popq %rbx
   movq %rax, -16(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *16(%rax)
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

B$getP:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   movq %rax, %rdi
   call put
   movq $420, %rax
   movq -8(%rbp), %rbx
   movq %rax, 16(%rbx)
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

