   .data
Element$$:
   .quad Element$GetSalary
   .quad Element$Init
   .quad Element$GetAge
   .quad Element$Equal
   .quad Element$GetMarried
   .quad Element$Compare
List$$:
   .quad List$Delete
   .quad List$Print
   .quad List$Init
   .quad List$GetElem
   .quad List$GetNext
   .quad List$SetNext
   .quad List$Search
   .quad List$GetEnd
   .quad List$InitNew
   .quad List$Insert
LL$$:
   .quad LL$Start
   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $8, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq LL$$, %rdx
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
Element$Init:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq %rcx
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 24(%rbx)
   movq -24(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 8(%rbx)
   movq -32(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 16(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Element$GetAge:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Element$GetSalary:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Element$GetMarried:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Element$Equal:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq $1, %rax
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *16(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   movq -32(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *40(%rax)
   popq %rbx
   xorq $1, %rax
   cmpq $0, %rax
   je else$L0
   movq $0, %rax
   movq %rax, -24(%rbp)
   jmp done$L1
else$L0:
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *0(%rax)
   popq %rbx
   movq %rax, -40(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *40(%rax)
   popq %rbx
   xorq $1, %rax
   cmpq $0, %rax
   je else$L2
   movq $0, %rax
   movq %rax, -24(%rbp)
   jmp done$L3
else$L2:
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   cmpq $0, %rax
   je else$L4
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   xorq $1, %rax
   cmpq $0, %rax
   je else$L6
   movq $0, %rax
   movq %rax, -24(%rbp)
   jmp done$L7
else$L6:
   movq $0, %rax
   movq %rax, -48(%rbp)
done$L7:
   jmp done$L5
else$L4:
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L8
   movq $0, %rax
   movq %rax, -24(%rbp)
   jmp done$L9
else$L8:
   movq $0, %rax
   movq %rax, -48(%rbp)
done$L9:
done$L5:
done$L3:
done$L1:
   movq -24(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Element$Compare:
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
   je else$L10
   movq $0, %rax
   movq %rax, -32(%rbp)
   jmp done$L11
else$L10:
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
   je else$L12
   movq $0, %rax
   movq %rax, -32(%rbp)
   jmp done$L13
else$L12:
   movq $1, %rax
   movq %rax, -32(%rbp)
done$L13:
done$L11:
   movq -32(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$Init:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq $1, %rax
   movq -8(%rbp), %rbx
   movq %rax, 24(%rbx)
   movq $1, %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$InitNew:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq %rcx
   movq -32(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 24(%rbx)
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 8(%rbx)
   movq -24(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 16(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$Insert:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   movq -8(%rbp), %rax
   movq %rax, -32(%rbp)
   movq $32, %rdi
   call mjcalloc
   leaq List$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -40(%rbp)
   movq -40(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   movq -32(%rbp), %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rcx
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *64(%rax)
   movq %rax, -24(%rbp)
   movq -40(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$SetNext:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 16(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$Delete:
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
   pushq $0
   pushq $0
   movq -8(%rbp), %rax
   movq %rax, -24(%rbp)
   movq $0, %rax
   movq %rax, -32(%rbp)
   movq $0, %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, -80(%rbp)
   movq -8(%rbp), %rax
   movq %rax, -48(%rbp)
   movq -8(%rbp), %rax
   movq %rax, -56(%rbp)
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   movq %rax, -64(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   movq %rax, -72(%rbp)
test$L14:
   movq -64(%rbp), %rax
   xorq $1, %rax
   cmpq $0, %rax
   je done$L16
   movq -32(%rbp), %rax
   xorq $1, %rax
done$L16:
   cmpq $0, %rax
   je done$L15
   movq -16(%rbp), %rax
   pushq %rax
   movq -72(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   cmpq $0, %rax
   je else$L17
   movq $1, %rax
   movq %rax, -32(%rbp)
   movq -80(%rbp), %rax
   pushq %rax
   movq $0, %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L19
   movq -48(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *32(%rax)
   movq %rax, -24(%rbp)
   jmp done$L20
else$L19:
   movq $0, %rax
   pushq %rax
   movq $555, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, %rdi
   call put
   movq -56(%rbp), %rax
   pushq %rax
   movq -48(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *40(%rax)
   movq %rax, -40(%rbp)
   movq $0, %rax
   pushq %rax
   movq $555, %rax
   popq %rdx
   subq %rax, %rdx
   movq %rdx, %rax
   movq %rax, %rdi
   call put
done$L20:
   jmp done$L18
else$L17:
   movq $0, %rax
   movq %rax, -88(%rbp)
done$L18:
   movq -32(%rbp), %rax
   xorq $1, %rax
   cmpq $0, %rax
   je else$L21
   movq -48(%rbp), %rax
   movq %rax, -56(%rbp)
   movq -48(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *32(%rax)
   movq %rax, -48(%rbp)
   movq -48(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *56(%rax)
   movq %rax, -64(%rbp)
   movq -48(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   movq %rax, -72(%rbp)
   movq $1, %rax
   movq %rax, -80(%rbp)
   jmp done$L22
else$L21:
   movq $0, %rax
   movq %rax, -88(%rbp)
done$L22:
   jmp test$L14
done$L15:
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
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$Search:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq $0, %rax
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rax
   movq %rax, -32(%rbp)
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   movq %rax, -48(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   movq %rax, -40(%rbp)
test$L23:
   movq -48(%rbp), %rax
   xorq $1, %rax
   cmpq $0, %rax
   je done$L24
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   cmpq $0, %rax
   je else$L25
   movq $1, %rax
   movq %rax, -24(%rbp)
   jmp done$L26
else$L25:
   movq $0, %rax
   movq %rax, -56(%rbp)
done$L26:
   movq -32(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *32(%rax)
   movq %rax, -32(%rbp)
   movq -32(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *56(%rax)
   movq %rax, -48(%rbp)
   movq -32(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   movq %rax, -40(%rbp)
   jmp test$L23
done$L24:
   movq -24(%rbp), %rax
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
List$GetEnd:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$GetElem:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$GetNext:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
List$Print:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   pushq $0
   pushq $0
   movq -8(%rbp), %rax
   movq %rax, -16(%rbp)
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   movq %rax, -32(%rbp)
test$L27:
   movq -24(%rbp), %rax
   xorq $1, %rax
   cmpq $0, %rax
   je done$L28
   movq -32(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *16(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *56(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   jmp test$L27
done$L28:
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
LL$Start:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq $32, %rdi
   call mjcalloc
   leaq List$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -24(%rbp)
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *16(%rax)
   movq %rax, -32(%rbp)
   movq -24(%rbp), %rax
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *16(%rax)
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq $32, %rdi
   call mjcalloc
   leaq Element$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -40(%rbp)
   movq -40(%rbp), %rax
   pushq %rax
   movq $25, %rax
   pushq %rax
   movq $37000, %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rcx
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *72(%rax)
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq $10000000, %rax
   movq %rax, %rdi
   call put
   movq $32, %rdi
   call mjcalloc
   leaq Element$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -40(%rbp)
   movq -40(%rbp), %rax
   pushq %rax
   movq $39, %rax
   pushq %rax
   movq $42000, %rax
   pushq %rax
   movq $1, %rax
   pushq %rax
   popq %rcx
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq -40(%rbp), %rax
   movq %rax, -48(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *72(%rax)
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq $10000000, %rax
   movq %rax, %rdi
   call put
   movq $32, %rdi
   call mjcalloc
   leaq Element$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -40(%rbp)
   movq -40(%rbp), %rax
   pushq %rax
   movq $22, %rax
   pushq %rax
   movq $34000, %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rcx
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *72(%rax)
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq $32, %rdi
   call mjcalloc
   leaq Element$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -56(%rbp)
   movq -56(%rbp), %rax
   pushq %rax
   movq $27, %rax
   pushq %rax
   movq $34000, %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rcx
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -48(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *48(%rax)
   movq %rax, %rdi
   call put
   movq -16(%rbp), %rax
   pushq %rax
   movq -56(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *48(%rax)
   movq %rax, %rdi
   call put
   movq $10000000, %rax
   movq %rax, %rdi
   call put
   movq $32, %rdi
   call mjcalloc
   leaq Element$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -40(%rbp)
   movq -40(%rbp), %rax
   pushq %rax
   movq $28, %rax
   pushq %rax
   movq $35000, %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rcx
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *72(%rax)
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq $2220000, %rax
   movq %rax, %rdi
   call put
   movq -16(%rbp), %rax
   pushq %rax
   movq -48(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq $33300000, %rax
   movq %rax, %rdi
   call put
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *0(%rax)
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq $44440000, %rax
   movq %rax, %rdi
   call put
   movq $0, %rax
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
