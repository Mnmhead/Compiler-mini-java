   .data
QS$$:
   .quad QS$Print
   .quad QS$Init
   .quad QS$Start
   .quad QS$Sort
   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $24, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq QS$$, %rdx
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
QS$Start:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   movq %rax, -24(%rbp)
   movq $9999, %rax
   movq %rax, %rdi
   call put
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   movq %rax, -24(%rbp)
   movq $0, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
QS$Sort:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq $0, %rax
   movq %rax, -64(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L0
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -24(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -40(%rbp)
   movq -24(%rbp), %rax
   movq %rax, -48(%rbp)
   movq $1, %rax
   movq %rax, -72(%rbp)
test$L2:
   movq -72(%rbp), %rax
   cmpq $0, %rax
   je done$L3
   movq $1, %rax
   movq %rax, -80(%rbp)
test$L4:
   movq -80(%rbp), %rax
   cmpq $0, %rax
   je done$L5
   movq -40(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -40(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -40(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   movq %rax, -88(%rbp)
   movq -88(%rbp), %rax
   pushq %rax
   movq -32(%rbp), %rax
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
   movq %rax, -80(%rbp)
   jmp done$L7
else$L6:
   movq $1, %rax
   movq %rax, -80(%rbp)
done$L7:
   jmp test$L4
done$L5:
   movq $1, %rax
   movq %rax, -80(%rbp)
test$L8:
   movq -80(%rbp), %rax
   cmpq $0, %rax
   je done$L9
   movq -48(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -48(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -48(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   movq %rax, -88(%rbp)
   movq -32(%rbp), %rax
   pushq %rax
   movq -88(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   xorq $1, %rax
   cmpq $0, %rax
   je else$L10
   movq $0, %rax
   movq %rax, -80(%rbp)
   jmp done$L11
else$L10:
   movq $1, %rax
   movq %rax, -80(%rbp)
done$L11:
   jmp test$L8
done$L9:
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -40(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   movq %rax, -64(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -48(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -48(%rbp), %rax
   pushq %rax
   movq -64(%rbp), %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -48(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L12
   movq $0, %rax
   movq %rax, -72(%rbp)
   jmp done$L13
else$L12:
   movq $1, %rax
   movq %rax, -72(%rbp)
done$L13:
   jmp test$L2
done$L3:
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -48(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -40(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -24(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   movq -64(%rbp), %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   movq %rax, -56(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   movq %rax, -56(%rbp)
   jmp done$L1
else$L0:
   movq $0, %rax
   movq %rax, -56(%rbp)
done$L1:
   movq $0, %rax
   popq %rbx
   popq %rbx
   popq %rbx
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
QS$Print:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movq $0, %rax
   movq %rax, -16(%rbp)
test$L14:
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
   je done$L15
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
   jmp test$L14
done$L15:
   movq $0, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
QS$Init:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 16(%rbx)
   movq -16(%rbp), %rax
   movq %rax, %rdi
   pushq %rax
   addq $1, %rdi
   imulq $8, %rdi
   call mjcalloc
   popq %rbx
   movq %rbx, (%rax)
   movq -8(%rbp), %rbx
   movq %rax, 8(%rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   movq $20, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $1, %rax
   pushq %rax
   movq $7, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $2, %rax
   pushq %rax
   movq $12, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $3, %rax
   pushq %rax
   movq $18, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $4, %rax
   pushq %rax
   movq $2, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $5, %rax
   pushq %rax
   movq $11, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $6, %rax
   pushq %rax
   movq $6, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $7, %rax
   pushq %rax
   movq $9, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $8, %rax
   pushq %rax
   movq $19, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq $9, %rax
   pushq %rax
   movq $5, %rax
   popq %rbx
   popq %rcx
   incq %rbx
   imulq $8, %rbx
   movq %rax, (%rcx, %rbx)
   movq $0, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
