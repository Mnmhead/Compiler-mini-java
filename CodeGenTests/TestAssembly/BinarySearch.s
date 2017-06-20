   .data
BS$$:
   .quad BS$Div
   .quad BS$Print
   .quad BS$Init
   .quad BS$Start
   .quad BS$Search
   .quad BS$Compare
   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $24, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq BS$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   movq $20, %rax
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
   movq %rbp, %rsp
   popq %rbp
   ret
BS$Start:
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
   call *16(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *8(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   movq $8, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L0
   movq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   jmp done$L1
else$L0:
   movq $0, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
done$L1:
   movq -8(%rbp), %rax
   pushq %rax
   movq $19, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L2
   movq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   jmp done$L3
else$L2:
   movq $0, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
done$L3:
   movq -8(%rbp), %rax
   pushq %rax
   movq $20, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L4
   movq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   jmp done$L5
else$L4:
   movq $0, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
done$L5:
   movq -8(%rbp), %rax
   pushq %rax
   movq $21, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L6
   movq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   jmp done$L7
else$L6:
   movq $0, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
done$L7:
   movq -8(%rbp), %rax
   pushq %rax
   movq $37, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L8
   movq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   jmp done$L9
else$L8:
   movq $0, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
done$L9:
   movq -8(%rbp), %rax
   pushq %rax
   movq $38, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L10
   movq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   jmp done$L11
else$L10:
   movq $0, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
done$L11:
   movq -8(%rbp), %rax
   pushq %rax
   movq $39, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L12
   movq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   jmp done$L13
else$L12:
   movq $0, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
done$L13:
   movq -8(%rbp), %rax
   pushq %rax
   movq $50, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L14
   movq $1, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   jmp done$L15
else$L14:
   movq $0, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
done$L15:
   movq $999, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
BS$Search:
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
   pushq $0
   movq $0, %rax
   movq %rax, -64(%rbp)
   movq $0, %rax
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   movq (%rax), %rax
   movq %rax, -32(%rbp)
   movq -32(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -32(%rbp)
   movq $0, %rax
   movq %rax, -40(%rbp)
   movq $1, %rax
   movq %rax, -48(%rbp)
test$L16:
   movq -48(%rbp), %rax
   cmpq $0, %rax
   je done$L17
   movq -40(%rbp), %rax
   pushq %rax
   movq -32(%rbp), %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -56(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   movq -56(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   movq %rax, -56(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -56(%rbp), %rax
   popq %rbx
   incq %rax
   imulq $8, %rax
   movq (%rbx, %rax), %rax
   movq %rax, -64(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -64(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L18
   movq -56(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -32(%rbp)
   jmp done$L19
else$L18:
   movq -56(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -40(%rbp)
done$L19:
   movq -8(%rbp), %rax
   pushq %rax
   movq -64(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *40(%rax)
   cmpq $0, %rax
   je else$L20
   movq $0, %rax
   movq %rax, -48(%rbp)
   jmp done$L21
else$L20:
   movq $1, %rax
   movq %rax, -48(%rbp)
done$L21:
   movq -32(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L22
   movq $0, %rax
   movq %rax, -48(%rbp)
   jmp done$L23
else$L22:
   movq $0, %rax
   movq %rax, -72(%rbp)
done$L23:
   jmp test$L16
done$L17:
   movq -8(%rbp), %rax
   pushq %rax
   movq -64(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *40(%rax)
   cmpq $0, %rax
   je else$L24
   movq $1, %rax
   movq %rax, -24(%rbp)
   jmp done$L25
else$L24:
   movq $0, %rax
   movq %rax, -24(%rbp)
done$L25:
   movq -24(%rbp), %rax
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
BS$Div:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   movq $0, %rax
   movq %rax, -24(%rbp)
   movq $0, %rax
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -40(%rbp)
test$L26:
   movq -32(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je done$L27
   movq -24(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -24(%rbp)
   movq -32(%rbp), %rax
   pushq %rax
   movq $2, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -32(%rbp)
   jmp test$L26
done$L27:
   movq -24(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
BS$Compare:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq $0
   pushq $0
   movq $0, %rax
   movq %rax, -32(%rbp)
   movq -24(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -40(%rbp)
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
   je else$L28
   movq $0, %rax
   movq %rax, -32(%rbp)
   jmp done$L29
else$L28:
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   xorq $1, %rax
   cmpq $0, %rax
   je else$L30
   movq $0, %rax
   movq %rax, -32(%rbp)
   jmp done$L31
else$L30:
   movq $1, %rax
   movq %rax, -32(%rbp)
done$L31:
done$L29:
   movq -32(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
BS$Print:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   movq $1, %rax
   movq %rax, -16(%rbp)
test$L32:
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
   je done$L33
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
   jmp test$L32
done$L33:
   movq $99999, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq $0, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
BS$Init:
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
   movq %rax, %rdi
   pushq %rax
   addq $1, %rdi
   imulq $8, %rdi
   call mjcalloc
   popq %rbx
   movq %rbx, (%rax)
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
test$L34:
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
   je done$L35
   movq $2, %rax
   pushq %rax
   movq -24(%rbp), %rax
   popq %rdx
   imulq %rdx, %rax
   movq %rax, -48(%rbp)
   movq -32(%rbp), %rax
   pushq %rax
   movq $3, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -40(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   movq -48(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
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
   jmp test$L34
done$L35:
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
