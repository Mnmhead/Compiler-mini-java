   .data
Animal$$:
   .quad Animal$addLegs
   .quad Animal$getSize
   .quad Animal$type
LizardBro$$:
   .quad Animal$addLegs
   .quad LizardBro$getSize
   .quad Animal$type
   .quad LizardBro$getTailSize
   .quad LizardBro$mainLizard
   .quad LizardBro$setSize
   .quad LizardBro$getTotalSize
   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $56, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq LizardBro$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   movq $77, %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   movq $10, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Animal$getSize:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Animal$addLegs:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   pushq %rax
   movq -16(%rbp), %rax
   popq %rdx
   addq %rdx, %rax
   movq -8(%rbp), %rbx
   movq %rax, 24(%rbx)
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Animal$type:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   cmpq $0, %rax
   je else$L0
   movq $1, %rax
   movq %rax, -16(%rbp)
   jmp done$L1
else$L0:
   movq $0, %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -16(%rbp)
done$L1:
   movq -8(%rbp), %rbx
   movq 32(%rbx), %rax
   cmpq $0, %rax
   je else$L2
   movq $420, %rax
   movq %rax, -16(%rbp)
   jmp done$L3
else$L2:
   movq $0, %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -16(%rbp)
done$L3:
   movq -16(%rbp), %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LizardBro$mainLizard:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq $0
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *40(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *48(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -8(%rbp), %rax
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
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq $99999, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LizardBro$getTotalSize:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 48(%rbx), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   popq %rdx
   addq %rdx, %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LizardBro$getSize:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LizardBro$getTailSize:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 48(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LizardBro$setSize:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   movq -24(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 8(%rbx)
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 48(%rbx)
   movq $0, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
