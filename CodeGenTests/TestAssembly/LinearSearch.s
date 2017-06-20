   .data
LS$$:
   .quad LS$Print
   .quad LS$Init
   .quad LS$Start
   .quad LS$Search
   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $24, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq LS$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   movq $10, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *16(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LS$Start:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *8(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *0(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq $9999, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -8(%rbp), %rax
   pushq %rax
   movq $8, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -8(%rbp), %rax
   pushq %rax
   movq $12, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -8(%rbp), %rax
   pushq %rax
   movq $17, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -8(%rbp), %rax
   pushq %rax
   movq $50, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq $55, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LS$Print:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movq $1, %rax
   movq %rax, -16(%rbp)
test$L0:
   movq -16(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je done$L1
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -16(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -16(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -16(%rbp)
   jmp test$L0
done$L1:
   movq $0, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LS$Search:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq $1, %rax
   movq %rax, -24(%rbp)
   movq $0, %rax
   movq %rax, -32(%rbp)
   movq $0, %rax
   movq %rax, -40(%rbp)
test$L2:
   movq -24(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je done$L3
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -24(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   movq %rax, -48(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -56(%rbp)
   movq -48(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L4
   movq $0, %rax
   movq %rax, -64(%rbp)
   jmp done$L5
else$L4:
   movq -48(%rbp), %rax
   pushq %rax
   movq -56(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   xorq $1, %rax
   cmpq $0, %rax
   je else$L6
   movq $0, %rax
   movq %rax, -64(%rbp)
   jmp done$L7
else$L6:
   movq $1, %rax
   movq %rax, -32(%rbp)
   movq $1, %rax
   movq %rax, -40(%rbp)
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   movq %rax, -24(%rbp)
done$L7:
done$L5:
   movq -24(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -24(%rbp)
   jmp test$L2
done$L3:
   movq -40(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LS$Init:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 16(%rbx)
   movq -16(%rbp), %rax
   addq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   movq -8(%rbp), %rbx
   movq %rax, 8(%rbx)
   movq $1, %rax
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -32(%rbp)
test$L8:
   movq -24(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je done$L9
   movq $2, %rax
   pushq %rax
   movq -24(%rbp), %rax
   popq %rdx
   imulq %rdx, %rax
   movq %rax, -40(%rbp)
   movq -32(%rbp), %rax
   pushq %rax
   movq $3, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -48(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   movq -48(%rbp), %rax
   popq %rdx
   addq %rdx, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -24(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -24(%rbp)
   movq -32(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -32(%rbp)
   jmp test$L8
done$L9:
   movq $0, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
