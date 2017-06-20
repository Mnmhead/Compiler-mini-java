   .data
Goob$$:
   .quad Goob$getSize
   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $32, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq Goob$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
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
Goob$getSize:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq $69, %rax
   movq -8(%rbp), %rbx
   movq %rax, 2(%rbx)
   movq -8(%rbp), %rbx
   movq 2(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
