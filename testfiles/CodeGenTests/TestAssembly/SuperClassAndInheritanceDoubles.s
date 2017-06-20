   .data
Parent$$:
   .quad Parent$setX
   .quad Parent$overidden
   .quad Parent$inherited
Child$$:
   .quad Parent$setX
   .quad Child$overidden
   .quad Parent$inherited

double$lit$0:
   .double 8.88
double$lit$2:
   .double 33.33333
double$lit$1:
   .double 69.42

   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $32, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq Child$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   lea double$lit$0, %rbx
   movq (%rbx), %rax
   pushq %rax
   movsd (%rsp), %xmm0
   popq %rbx
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *16(%rax)
   popq %rbx
   pushq %rax
   movsd (%rsp), %xmm0
   popq %rax
   pushq %rbx
   call putD
   popq %rbx
   movq $32, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq Child$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   lea double$lit$1, %rbx
   movq (%rbx), %rax
   pushq %rax
   movsd (%rsp), %xmm0
   popq %rbx
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *8(%rax)
   popq %rbx
   pushq %rax
   movsd (%rsp), %xmm0
   popq %rax
   pushq %rbx
   call putD
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

Parent$inherited:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movsd %xmm0, (%rsp)
   movq -16(%rbp), %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

Parent$setX:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movsd %xmm0, (%rsp)
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 8(%rbx)
   movq $0, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

Parent$overidden:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movsd %xmm0, (%rsp)
   movq -16(%rbp), %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

Child$overidden:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movsd %xmm0, (%rsp)
   pushq $0
   movq -8(%rbp), %rax
   pushq %rax
   lea double$lit$2, %rbx
   movq (%rbx), %rax
   pushq %rax
   movsd (%rsp), %xmm0
   popq %rbx
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret

